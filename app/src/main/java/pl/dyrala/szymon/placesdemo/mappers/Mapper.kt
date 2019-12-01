package pl.dyrala.szymon.placesdemo.mappers

interface Mapper<TFrom, TTo> {

    fun map(from: TFrom): TTo
}