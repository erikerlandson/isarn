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

package org.isarnproject.algebirdAlgebraAPI

import scala.language.implicitConversions

import com.twitter.algebird.{ Semigroup, Monoid, MonoidAggregator }

import org.isarnproject.algebraAPI._

object implicits {
  implicit def algebirdImplicitSemigroupToIsarn[A](implicit sg: Semigroup[A]): SemigroupAPI[A] =
    algebirdSemigroupToIsarn(sg)

  implicit def algebirdSemigroupToIsarn[A](sg: Semigroup[A]): SemigroupAPI[A] =
    new SemigroupAPI[A] {
      def combine(x: A, y: A): A = sg.plus(x, y)
      def combineAllOption(as: TraversableOnce[A]): Option[A] = sg.sumOption(as)
    }

  implicit def algebirdImplicitMonoidToIsarn[A](implicit m: Monoid[A]): MonoidAPI[A] =
    algebirdMonoidToIsarn(m)

  implicit def algebirdMonoidToIsarn[A](m: Monoid[A]): MonoidAPI[A] =
    new MonoidAPI[A] {
      def empty: A = m.zero
      def combine(x: A, y: A): A = m.plus(x, y)
      def combineAllOption(as: TraversableOnce[A]): Option[A] = m.sumOption(as)
      def combineAll(as: TraversableOnce[A]): A = m.sum(as)
    }

  implicit def algebirdImpMonoidAggregatorToIsarn[M, D](implicit agg: MonoidAggregator[D, M, M]):
      AggregatorAPI[M, D] =
    algebirdMonoidAggregatorToIsarn(agg)

  implicit def algebirdMonoidAggregatorToIsarn[M, D](agg: MonoidAggregator[D, M, M]):
      AggregatorAPI[M, D] =
  {
    val mval = algebirdMonoidToIsarn(agg.monoid)
    new AggregatorAPI[M, D] {
      def monoid: MonoidAPI[M] = mval
      def lff: (M, D) => M = agg.append
      def mf: D => M = agg.prepare
      def aggregate(as: TraversableOnce[D]): M = as.foldLeft(monoid.empty)(lff)
    }
  }
}
