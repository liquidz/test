.PHONY: build
build:
	docker build -t uochan/bb-test .

.PHONY: shadow-test
shadow-test:
	npx shadow-cljs compile ci
	./node_modules/karma/bin/karma start --single-run

.PHONY: clean
clean:
	rm -rf .cpcache target

.PHONY: repl
repl:
	iced repl --force-clojure-cli -A:dev

.PHONY: docker-up
docker-up:
	cd dev-docker && docker compose up -d

.PHONY: docker-down
docker-down:
	cd dev-docker && docker compose down
