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

package utils

import client.WsClient
import play.api.libs.json.Json

trait BaseRequests extends BaseUris {

  def createBearerToken(
    credId: String
  ): String = {

    def createLocalBearerToken(credId: String) = {
      val json =
        Json.obj(
          "credId"             -> credId,
          "excludeGnapToken"   -> true,
          "affinityGroup"      -> "Individual",
          "confidenceLevel"    -> 250,
          "credentialStrength" -> "strong",
          "enrolments"         -> Json.arr(),
          "nino"               -> "AA000003D",
          "itmpData"           -> Json.obj(
            "givenName"  -> "Performance Test User",
            "familyName" -> "Performance Test User",
            "birthdate"  -> "1948-04-23"
          )
        )

      val response                                     = WsClient.post(s"$authLoginApiUri/session/login", Map("Content-Type" -> "application/json"), json)
      val authHeader: (String, collection.Seq[String]) =
        response.headers.filter(header => header._1.equalsIgnoreCase("Authorization")).head
      val authBearerToken                              = authHeader._2.head.replace("Bearer ", "")
      authBearerToken
    }

    val bearerToken: String = createLocalBearerToken(credId)
    bearerToken
  }

}
