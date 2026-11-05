package steps.context

import models.ResponseBody
import models.search.ExploreResult

final case class ResponseContext (
                                   var responseBody: Option[ResponseBody] = None,
                                   var status: Int = 0,
                                   var headers: Map[String, String] = Map.empty
)
