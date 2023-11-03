package com.example.sessionunittest.modules

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

class FakeUserRepository: UserRepository {

    val fakeDb: MutableMap<Int, User> = mutableMapOf()

    override fun findById(id: Int): Optional<User> {
        return Optional.ofNullable(fakeDb[id])
    }

    override fun <S : User?> save(entity: S & Any): S & Any {
        fakeDb[entity.id] = entity
        return entity
    }

    override fun <S : User?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : User?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : User?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<User> {
        TODO("Not yet implemented")
    }

    override fun <S : User?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : User?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: User) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<User>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : User?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : User?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : User?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R & Any {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : User?> saveAndFlush(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : User?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<User>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }


}