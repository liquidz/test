(ns foo.core-test
  (:require
    [clojure.string :as str]
    [clojure.test :as t]
    [cognitect.aws.client.api :as aws]
    [foo.core :as sut]
    [gluttony.core :as gluttony]))

(t/deftest bar-test
  (t/is (= 2 (sut/bar 1))))
;; {:region #keyword #or [#env AWS_REGION "ap-northeast-2"]
;;  :queue-name #or [#env FIFO_QUEUE_NAME "local-queue.fifo"]}

(def ^:private queue-name "local-queue.fifo")

(defn- get-client
  []
  (aws/client {:api :sqs
               :region :ap-northeast-1
               :endpoint-override {:protocol :http
                                   :hostname "localhost"
                                   :port 9324
                                   :path ""}}))


(t/deftest local-sqs-test
  (let [client (get-client)
        queue-url (:QueueUrl (aws/invoke client {:op :GetQueueUrl :request {:QueueName queue-name}}))
        uuid (str (java.util.UUID/randomUUID))]
    (t/is (str/starts-with? queue-url "http://localhost:9324/"))
    (aws/invoke client {:op :PurgeQueue :request {:QueueUrl queue-url}})

    (println "sent\t\t" uuid)
    (let [x (aws/invoke client {:op :SendMessage
                                :request {:QueueUrl queue-url
                                          :MessageBody (str "body-" uuid)
                                          :MessageDeduplicationId (str "dup-" uuid)
                                          :MessageGroupId uuid}})]
      (list x))

    (let [messages (atom [])
          consume (fn [message respond _]
                    (println "received\t\t" (get-in message [:attributes :message-group-id]))
                    (respond)
                    (respond)
                    (swap! messages conj message))
          consumer (gluttony/start-consumer queue-url
                                            consume
                                            {:client client
                                             :num-workers 1
                                             :num-receivers 1
                                             :long-polling-duration 10})]
      (while (< (count @messages) 1)
        (Thread/sleep 500))

      (t/is (= 1 (count @messages)))
      (t/is (= uuid (get-in @messages [0 :attributes :message-group-id])))
      (Thread/sleep 1000)
      (gluttony/stop-consumer consumer))))


