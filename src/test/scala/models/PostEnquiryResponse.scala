package models

import play.api.libs.json.{Json, OFormat}

case class PostEnquiryResponse(message:String)
object PostEnquiryResponse :
    implicit val format: OFormat[PostEnquiryResponse] = Json.format

