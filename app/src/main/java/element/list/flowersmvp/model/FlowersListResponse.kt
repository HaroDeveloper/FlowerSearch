package element.list.flowersmvp.model

data class FlowersListResponse(
    var flowers: MutableList<Flower>,
    var meta: PaginationInfo
)