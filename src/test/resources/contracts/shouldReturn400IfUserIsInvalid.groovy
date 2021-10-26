package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return 400 if user is invalid when attempting to create a task"
    request {
        method PUT()
        url "/user/2/task"
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
        status 400
    }
}