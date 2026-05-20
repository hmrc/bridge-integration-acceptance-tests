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

object PropertyLinkingRequestBuilder extends BaseRequests {

  def propertyLinkingRequest(credId: String, assessmentId: String): StandaloneWSResponse = {
    val bearerToken  = createBearerToken(credId = credId)
    val assessmentId = "27399677000"

    val baseUri = s"$bridgeIntegrationUrl/property-linking/$credId/relationship-change/$assessmentId"

    println(s"service level bearer token ********************************* $bearerToken")

    val headers = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.1.0+json"
    )

    val jsonBody = Json.parse("""
        |    {"id":13,"idx":"1.13.1","name":"Property Link","label":"Ratepayer-ListEntry-Property","description":"A relationships between LGFA88shd9para4J person-personas and LGFA88 hereditaments for which such personas are obliged to provide LGFA88shd9para4I(1) notofiable information.","origination":null,"termination":null,"category":{"code":"LTX-DOM-REL","meaning":"Local taxation domain relationship"},"type":{"code":"LIB","meaning":"Liability | One entity is liable for other entity(s)"},"class":{"code":"LOC","meaning":"Local Non Domestic Rating Occupied Hereditament Charge"},"data":{"foreign_ids":[],"foreign_names":[],"foreign_labels":[],"manifestations":[{"artifact_reference":null,"artifact_code":"NRB","artifact_description":null,"issued_date":null,"withdrawn_date":null,"effective_from_date":null,"effective_to_date":null,"observed_date":null,"operative_area_code":null,"operative_area_name":null,"protodata_ptr":"https://hmrc/sdes/yry64849ree","notes":null}]},"protodata":[],"metadata":{"sending":{"extracting":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}},"transforming":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}},"loading":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}}},"receiving":{"unloading":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}},"transforming":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}},"storing":{"selecting":{},"filtering":{},"supplementing":{},"recontextualising":{},"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{},"receiving":{},"decrypting":{},"verifying":{},"dropping":{},"restoring":{},"inserting":{}}}},"compartments":{},"items":[{"transportation":{"path":"/job/compartments/properties/@id=13/data/assessments/@id=13"},"persistence":{"place":"LTX-DOM-AST","identifier":13}},{"transportation":{"path":"/job/compartments/persons/@id=16/items/@id=13"},"persistence":{"place":"LTX-DOM-PSA","identifier":13}}]}
        |""".stripMargin)

    WsClient.post(
      uri = baseUri,
      headers = headers,
      json = jsonBody
    )
  }
}
