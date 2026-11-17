/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package builders

import models.search.PostcodeSearchRequest
import client.WsClient
import play.api.libs.ws.StandaloneWSResponse
import utils.BaseRequests

object SearchPostcodeListRequestBuilder extends BaseRequests {

  def getPostcodeData(
    credId: String,
    request: PostcodeSearchRequest
  ): StandaloneWSResponse = {
    val bearerToken = createBearerToken(credId = credId)
    val queryParams = Seq(
      Some("postcode" -> request.postcode),
      Some("listType" -> request.listType),
      request.page.map("page" -> _),
      request.page_size.map("page_size" -> _),
      request.propertyName.map("propertyName" -> _),
      request.street.map("street" -> _),
      request.town.map("town" -> _),
      request.councilTaxBand.map("councilTaxBand" -> _),
      request.bandStatus.map("bandStatus" -> _),
      request.localAuthority.map("localAuthority" -> _),
      request.localAuthorityReferenceNumber.map("localAuthorityReferenceNumber" -> _),
      request.courtCode.map("courtCode" -> _),
      request.propertyUse.map("propertyUse" -> _)
    ).flatten

    val queryString = queryParams.map { case (k, v) => s"$k=$v" }.mkString("&")
    val baseUri     = s"$bridgeIntegrationUrl/search?$queryString"
    println(s"service level bearer token *********************************$bearerToken")
    val headers     = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.1.0+json"
    )
    WsClient.get(baseUri, headers = headers)
  }

}
