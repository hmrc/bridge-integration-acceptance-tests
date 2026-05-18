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

import builders.ExploreRatepayerRequestBuilder.{bridgeIntegrationUrl, createBearerToken}
import client.WsClient
import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSResponse
import utils.BaseRequests

object RegisterRatepayerRequestBuilder extends BaseRequests {

  def registerRatepayer(credId: String): StandaloneWSResponse = {
    val bearerToken = createBearerToken(credId = credId)

    val baseUri = s"$bridgeIntegrationUrl/register-ratepayer/$credId"

    println(s"service level bearer token ********************************* $bearerToken")

    val headers = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.1.0+json"
    )

    val jsonBody = Json.parse("""
        |    {"ratepayerCredId":"Int-506baac0-0bbe-456f-9a55-e29e40eec97b","name":"Jake","contactNumber":"07943009560","isRegistered":true,"recoveryId":"2NC3-L7NT-J9QR"}
        |""".stripMargin)

    WsClient.post(
      uri = baseUri,
      headers = headers,
      json = jsonBody
    )
  }
}
