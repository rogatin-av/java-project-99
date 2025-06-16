clean:
	./gradlew clean
run:
	./gradlew bootRun
build:
	./gradlew clean build
install:
	./gradlew installDist
lint:
	./gradlew checkstyleMain
test:
	./gradlew test
report:
	./gradlew jacocoTestReport

.PHONY: build