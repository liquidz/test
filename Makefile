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
