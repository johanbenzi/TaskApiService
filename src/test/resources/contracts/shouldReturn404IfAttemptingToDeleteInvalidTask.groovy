package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return 404 if attempting to delete a task that doesn't exist"
    request {
        method DELETE()
        url "/user/1/task/2"
        headers {
            accept(applicationJson())
            contentType(applicationJson())
        }
    }
    response {
        status 404
    }
}