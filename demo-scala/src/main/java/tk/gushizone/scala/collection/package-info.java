/**
 * 1. scala 集合体系主要包括：Iterable、 Seq(IndexSeq)、Set (Sortedset)、Map (SortedMap)．其中，Iterable是所有集合的trait。实际
 * ISeq、 Set、 Map都是Iterable的了 trait
 *   - Seq: 是一个由先后顺序的值的序列，比如，数组和列表(list).IndexSeq 允许我们通过整型的素引快速的访问任意元素。ArrayBuffer带索引的序列，但是链表没有.
 *   - Set: 一组没有顺序的值的集合。在Sortedset中，元素以某种排过序的顺序访问
 *   - Map，一组k，v对偶的集合，SortedMap 按照键的排序的实体
 * 2. scala 的集合几乎都是可以分为可变和不可变樂合。分别对成了2个包，scala.collection.mutable 和 scala.collection.immutable 两个包，前者是可变集合的包，后者为不可变集合的包
 * 3. Seq下包含了Range、 ArrayBuffer、List等子trait。其中Range表示一个序列，通过可以使用 1 to 10 的语法生成。ArrayBuffer就类似于 ArrayList
 */
package tk.gushizone.scala.collection;