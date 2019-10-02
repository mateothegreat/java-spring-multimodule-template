IMAGE ?= platform-api:0.0.1

.PHONY: build

jar:

	./gradlew bootJar

jar-run: jar

	cd application/build/libs && java -jar platform-api-0.0.1.jar

build:

	@echo "Building an image with the current tag $(IMAGE).."

	./gradlew bootJar

	docker build 	--rm 	\
					--tag $(IMAGE) .

