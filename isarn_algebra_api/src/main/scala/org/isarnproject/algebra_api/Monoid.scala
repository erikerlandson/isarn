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

package org.isarnproject.algebra_api


trait Monoid[A] extends Any with Semigroup[A] {
  /**
   * Return the identity element for this monoid.
   */
  def empty: A

  /**
   * Given a sequence of `as`, sum them using the monoid and return the total.
   */
  def combineAll(as: TraversableOnce[A]): A

}
