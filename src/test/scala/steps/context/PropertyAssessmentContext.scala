package steps.context

import models.PropertyAssessmentResponse

final case class PropertyAssessmentContext(
  var responseBody: Option[PropertyAssessmentResponse] = None,
  var status: Int = 0,
  var headers: Map[String, String] = Map.empty
)
