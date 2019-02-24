package com.example.johnqualls.recyclerviewdemo

fun List<Item>.deepCopy() = this.map{ it.copy() } as MutableList<Item>