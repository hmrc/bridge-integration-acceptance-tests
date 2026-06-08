package models

import play.api.libs.json.{Json, OFormat}

case class GetPropertyLinksResponse(message: String)

object GetPropertyLinksResponse:
  implicit val format: OFormat[GetPropertyLinksResponse] = Json.format
