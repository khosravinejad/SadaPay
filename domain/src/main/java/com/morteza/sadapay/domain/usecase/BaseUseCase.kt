package com.morteza.sadapay.domain.usecase

interface BaseUseCase<in Params, out Result> {
    suspend operator fun invoke(params: Params): Result
}