package com.example.ktor.utils

sealed class Either<out A, out B> {
    /** Левая ветвь. */
    data class Left<T>(val value: T) : Either<T, Nothing>()

    /** Правая ветвь. */
    data class Right<T>(val value: T) : Either<Nothing, T>()

    companion object
}

/** Трансформация правой части. */
inline infix fun <A, B, C> Either<A, B>.map(f: (B) -> C): Either<A, C> = when (this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(value))
}

/** Трансформация левой части. */
inline infix fun <A, B, C> Either<A, C>.mapLeft(f: (A) -> B): Either<B, C> = when (this) {
    is Either.Left -> Either.Left(f(value))
    is Either.Right -> this
}

/** Трансформация левой и правой частей. */
inline fun <A, B, C, D> Either<A, B>.bimap(leftOperation: (A) -> C, rightOperation: (B) -> D): Either<C, D> =
    when (this) {
        is Either.Left -> Either.Left(leftOperation(value))
        is Either.Right -> Either.Right(rightOperation(value))
    }

/** Сведение обеих ветвей к единому результату [C]. */
inline fun <A, B, C> Either<A, B>.fold(ifLeft: (A) -> C, ifRight: (B) -> C): C = when (this) {
    is Either.Left -> ifLeft(value)
    is Either.Right -> ifRight(value)
}
