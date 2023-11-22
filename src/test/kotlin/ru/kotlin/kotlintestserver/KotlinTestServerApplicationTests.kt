package ru.kotlin.kotlintestserver


import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import reactor.test.StepVerifier
import java.time.Duration


@SpringBootTest
class KotlinTestServerApplicationTests {

	@Test
	fun simpleMonoExample() {
		val monoColors = Mono.just("red")
		monoColors.subscribe { x: String? -> println(x) }
	}

	@Test
	fun simpleFluxExample() {
		val fluxColors = Flux.just("red", "green", "blue")
		fluxColors.subscribe { x: String? -> println(x) }
	}

	@Test
	fun onErrorExample() {
		val fluxCalc = Flux.just(-1, 0, 1)
			.map<String> { i: Int -> "10 / $i = " + (10 / i)  }
		fluxCalc.subscribe(
			{ value: String -> println("Next: $value") }
		) { error: Throwable -> System.err.println("Error: $error") }
	}

	@Test
	fun onErrorReturnExample() {
		val fluxCalc = Flux.just(-1, 0, 1)
			.map<String> { i: Int -> "10 / $i = " +  (10 / i) }
			.onErrorReturn(ArithmeticException::class.java, "Division by 0 not allowed")
		fluxCalc.subscribe(
			{ value: String -> println("Next: $value") }
		) { error: Throwable -> System.err.println("Error: $error") }
	}

	@Test
	fun stepVerifierTest() {
		val fluxCalc = Flux.just(-1, 0, 1)
			.map<String> { i: Int -> "10 / $i = " + (10 / i) }
		StepVerifier.create(fluxCalc)
			.expectNextCount(1)
			.expectError(ArithmeticException::class.java)
			.verify()
	}

	@Test
	fun publishSubscribeExample() {
		val schedulerA: Scheduler = Schedulers.newParallel("Scheduler A")
		val schedulerB: Scheduler = Schedulers.newParallel("Scheduler B")
		val schedulerC: Scheduler = Schedulers.newParallel("Scheduler C")
		Flux.just(1)
			.map { i: Int? ->
				println("First map: " + Thread.currentThread().name)
				i
			}
			.subscribeOn(schedulerA)
			.map { i: Int? ->
				println("Second map: " + Thread.currentThread().name)
				i
			}
			.publishOn(schedulerB)
			.map { i: Int? ->
				println("Third map: " + Thread.currentThread().name)
				i
			}
			.subscribeOn(schedulerC)
			.map { i: Int? ->
				println("Fourth map: " + Thread.currentThread().name)
				i
			}
			.publishOn(schedulerA)
			.map { i: Int? ->
				println("Fifth map: " + Thread.currentThread().name)
				i
			}
			.blockLast()
	}

	@Test
	@Throws(InterruptedException::class)
	fun coldPublisherExample() {
		val intervalFlux = Flux.interval(Duration.ofSeconds(1))
		Thread.sleep(2000)
		intervalFlux.subscribe { i: Long? ->
			println(
				String.format(
					"Subscriber A, value: %d",
					i
				)
			)
		}
		Thread.sleep(2000)
		intervalFlux.subscribe { i: Long? ->
			println(
				String.format(
					"Subscriber B, value: %d",
					i
				)
			)
		}
		Thread.sleep(3000)
	}

	@Test
	@Throws(InterruptedException::class)
	fun hotPublisherExample() {
		val intervalFlux = Flux.interval(Duration.ofSeconds(1))
		val intervalCF = intervalFlux.publish()
		intervalCF.connect()
		Thread.sleep(2000)
		intervalCF.subscribe { i: Long? ->
			println(
				String.format(
					"Subscriber A, value: %d",
					i
				)
			)
		}
		Thread.sleep(2000)
		intervalCF.subscribe { i: Long? ->
			println(
				String.format(
					"Subscriber B, value: %d",
					i
				)
			)
		}
		Thread.sleep(3000)
	}

}
