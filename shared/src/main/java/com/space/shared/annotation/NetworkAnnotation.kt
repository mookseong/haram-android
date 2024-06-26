package com.space.shared.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenEncrypted

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthEncrypted

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenAddHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SpaceLoginModule

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IntranetModule




