
def commonSettings = Seq(
  organization := "org.isarnproject",
  licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0")),
  version := "0.0.1",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
    ),
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt"),
  seq(bintraySettings:_*),
  seq(bintrayPublishSettings:_*)
)

lazy val isarn_scalatest = project.in(file("isarn_scalatest"))
  .settings(commonSettings:_*)
  .settings(
    name := "isarn-scalatest"
    )

lazy val isarn_algebra_api = project.in(file("isarn_algebra_api"))
  .settings(commonSettings:_*)
  .settings(
    name := "isarn-algebra-api"
    )

lazy val isarn_algebird_algebra_api = project.in(file("isarn_algebird_algebra_api"))
  .dependsOn(isarn_algebra_api)
  .settings(commonSettings:_*)
  .settings(
    name := "isarn-algebird-algebra-api",
    libraryDependencies ++= Seq(
      "com.twitter" %% "algebird-core" % "0.12.1"
      )
    )

lazy val isarn_collections = project.in(file("isarn_collections"))
  .dependsOn(isarn_scalatest % "test->test")
  .dependsOn(isarn_algebra_api)
  .dependsOn(isarn_algebird_algebra_api % "test->compile")
  .settings(commonSettings:_*)
  .settings(
    name := "isarn-collections"
    )
