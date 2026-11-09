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

package builders.CT

import builders.ExploreRatepayerRequestBuilder.{bridgeIntegrationUrl, createBearerToken}
import client.WsClient
import models.CT.enquiry.premises.{Information, Premise, Statement}
import models.CT.enquiry.product.ProductContext
import models.CT.enquiry.product.person.{Communications, Person}
import models.CT.enquiry.product.relationship.{Manifestations, Relationships}
import models.CT.enquiry.{EnquiryProtoData, Proposition, SubmissionRequest}
import models.CodeMeaning
import org.apache.pekko.http.scaladsl.model.DateTime
import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSResponse
import utils.BaseRequests

object EnquiryJourney1 extends BaseRequests {

  def submitJourney1(credId: String, listEntryId: String, listType: String): StandaloneWSResponse = {
    val bearerToken = createBearerToken(credId = credId)

    val baseUri = s"$bridgeIntegrationUrl/enquiries/$listEntryId/$listType"

    println(s"service level bearer token ********************************* $bearerToken")

    val headers = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.1.0+json"
    )

    val jsonBody = Json.toJson(
      SubmissionRequest(
        submissionId = "01", // UFS GENERATED
        submission_date = "", // AS SOON AS SUBMITTED
        files = List(
          EnquiryProtoData(
            node = None,
            mime_type = "application/pdf",
            label = "RYCTB Uploaded Document",
            is_pointer = true,
            pointer =
              "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
            data = None
          )
        ),
        product = ProductContext(
          Person(
            communications = Communications(
              forenames = Some("John"),
              surname = Some("Smith"),
              email = Some("john.smith@test.com"),
              telephone_number = Some("07700123456"),
              CouncilTaxPayerFirstName = None,
              CouncilTaxPayerLastName = None,
              CouncilTaxPayerEmail = None,
              CouncilTaxPayerNumber = None,
              CouncilTaxPayerContactAddress = Some("FLAT 1, 22 ST MARY STREET, CATHAYS, CARDIFF, CF10 1AA")
            ),
            communications_mode = None,
            capacity = CodeMeaning(
              code = Some("TXP"),
              meaning = Some("Council Tax Payer")
            ) // Are you responsible for the Council Tax on this property?
          ),
          relationship = Relationships(
            manifestation = Manifestations(
              artifact_code = "CTB",
              protodata_ptr = Some("https://www.sandwell.gov.uk/council-tax/council-tax-bill-explained")
            ),
            `type` = CodeMeaning(
              code = None,
              meaning = None
            ),
            `class` = CodeMeaning(
              code = None,
              meaning = None
            )
          )
        ),
        premises = Seq(
          Premise(
            // Reasons for reviewing your Council Tax band
            // How long have you been responsible for paying Council Tax on the property?
            // Tell us why you think the Council Tax band is wrong?
            unique_statutory_ref = "J01",
            text =
              "{\"Question\": \"How long have you been responsible for paying Council Tax on the property?\", \"Answer\": \"Less than six months\"}",
            statements = List(
              Statement(
                unique_statutory_ref = "S01",
                text =
                  "{\"Question\": \"Tell us why you think the Council Tax band is wrong?\", \"Answer\": \"Similar neighbouring properties of the same size and type are in a lower Council Tax Band\"}"
              )
            ),
            information = List(
              Information(
                unique_statutory_ref = "",
                text = ""
              )
            )
          )
        ),
        proposition = List(
          // When did you become responsible for paying Council Tax for your home?
          // What do you think the Council Tax band should be?
          Proposition(
            unique_statutory_ref = "PO1",
            alterations = List(
              "{\"Question\": \"What do you think your property's Council Tax band should be?\", \"Answer\": \"Band C\"}"
            )
          ),
          Proposition(
            unique_statutory_ref = "PO2",
            alterations = List(
              "{\"Question\": \"When did you become responsible for paying Council Tax for your home?\", \"Answer\": \"20260701T000000Z\"}"
            )
          )
        )
      )
    )

    WsClient.post(
      uri = baseUri,
      headers = headers,
      json = jsonBody
    )
  }
}
