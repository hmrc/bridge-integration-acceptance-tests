package builders

import client.WsClient
import play.api.libs.ws.StandaloneWSResponse
import utils.BaseRequests
import models.{EnquiryJobName, EnquiryRequest}

object CreateCTEnquiryRequestBuilder extends BaseRequests{

  def createCtEnquiry(credId: String, enquiryJobName: EnquiryJobName): StandaloneWSResponse = {
    val bearerToken = createBearerToken(credId = credId)

    val baseUri = s"$bridgeIntegrationUrl/enquiries/6A7D3B18-E92A-47AB-8795-82B6D1E78069/CVW"

    println(s"service level bearer token ********************************* $bearerToken")

    val headers = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type" -> "application/json",
      "Accept" -> "application/vnd.hmrc.1.0+json"
    )

    WsClient.post(
      uri = baseUri,
      headers = headers,
      json = EnquiryRequest.get(enquiryJobName)
    )
  }
}
