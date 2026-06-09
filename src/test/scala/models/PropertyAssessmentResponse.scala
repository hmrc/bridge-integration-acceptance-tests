package models

import play.api.libs.json.{Json, OFormat}

case class PropertyAssessmentResponse(message: String)

object PropertyAssessmentResponse:
  implicit val format: OFormat[PropertyAssessmentResponse] = Json.format
