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
 * Defines aggregation (aka map-reduce) on a monoidal aggregating type `M` and data type `D`
 */
trait AggregatorAPI[M, D] extends Any with Serializable {
  /**
   * Return the object defining the monoidal properties on type `M`
   */
  def monoid: MonoidAPI[M]

  /**
   * Return the left-folding function for this aggregator.
   *
   * The function `lff` obeys the properties:
   *
   * lff(m, d) = monoid.combine(m, lff(monoid.empty, d))
   *
   * lff(m, d) = monoid.combine(m, mf(d))
   */
  def lff: (M, D) => M

  /**
   * Return the mapping function for this aggregator.
   *
   * The function `mf` obeys the properties:
   *
   * monoid.combine(m, mf(d)) = lff(m, d)
   */
  def mf: D => M

  /**
   * Given a sequence of data `as`, aggregate it into a value of monoidal type M
   *
   * Aggregation has two equivalent definitions:
   *
   * aggregate(as) = monoid.combineAll(as.map(mf))  // The "map-reduce" definition
   *
   * aggregate(as) = as.foldLeft(monoid.empty)(lff) // The "left-folding" definition
   *
   * The left-folding definition is more efficient in general, and desirable for objects
   * implementing the `Aggregator` trait when possible.
   *
   * http://erikerlandson.github.io/blog/2016/09/05/expressing-map-reduce-as-a-left-folding-monoid
   */
  def aggregate(as: TraversableOnce[D]): M
}
