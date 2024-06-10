package com.example.movielistbycompose01


object Flavors {

    object Staging {
        const val name = "staging"
        const val suffix = ".${name}"
    }

    object Uat {
        const val name = "uat"
        const val suffix = ".${name}"
    }

    object Production {
        const val name = "production"
    }
}