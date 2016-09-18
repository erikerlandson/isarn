/*
Copyright 2016 Erik Erlandson

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.isarnproject.algebraAPI

/**
 * A semigroup is any type `A` with an associative operation (`combine`).
 * This object defines the `combine` option for some type `A`
 */
trait SemigroupAPI[A] extends Any with Serializable {

  /**
   * Associative operation taking which combines two values.
   */
  def combine(x: A, y: A): A

  /**
   * Given a sequence of `as`, combine them and return the total.
   *
   * If the sequence is empty, returns None. Otherwise, returns Some(total).
   */
  def combineAllOption(as: TraversableOnce[A]): Option[A]

}
