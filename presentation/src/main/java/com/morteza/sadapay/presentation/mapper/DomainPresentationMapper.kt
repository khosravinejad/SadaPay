package com.morteza.sadapay.presentation.mapper

abstract class DomainPresentationMapper<DOMAIN, PRESENTATION> {
    abstract fun mapToPresentation(input: DOMAIN): PRESENTATION
    abstract fun mapToDomain(input: PRESENTATION): DOMAIN
}