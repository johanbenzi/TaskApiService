package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should create a new task for a user"
    request {
        method PUT()
        url "/user/1/task"
        headers {
            accept(applicationJson())
            contentType(applicationJson())
        }
        body(
                title: anyNonBlankString(),
                description: anyNonBlankString(),
        )
    }
    response {
        status 201
        body(anyPositiveInt())
    }
}