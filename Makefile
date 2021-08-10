CURRENT_VERSION=$(shell cat version)

release:
	git tag $(CURRENT_VERSION)
	git push origin $(CURRENT_VERSION)

build:
	docker build -t uochan/bb-test .

shadow-test:
	npx shadow-cljs compile ci
	./node_modules/karma/bin/karma start --single-run
