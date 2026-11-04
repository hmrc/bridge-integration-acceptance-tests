package steps.context

import models.PostEnquiryResponse


final case class EnquiryContext(
                                 var responseBody: Option[PostEnquiryResponse] = None,
                                 var status: Int = 0,
                                 var headers: Map[String, String] = Map.empty
)
