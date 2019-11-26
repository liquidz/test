CURRENT_VERSION=$(shell cat version)

release:
	git tag $(CURRENT_VERSION)
	git push origin $(CURRENT_VERSION)
