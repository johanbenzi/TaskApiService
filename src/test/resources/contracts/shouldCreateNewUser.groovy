package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should create a new user"
    request {
        method PUT()
        url "/user"
        headers {
            accept(applicationJson())
            contentType(applicationJson())
        }
        body(anyNonBlankString())
    }
    response {
        status 201
        body(anyPositiveInt())
    }
}