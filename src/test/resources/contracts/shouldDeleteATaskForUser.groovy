package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should delete a task for a user"
    request {
        method DELETE()
        url "/user/1/task/1"
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
        status 204
    }
}