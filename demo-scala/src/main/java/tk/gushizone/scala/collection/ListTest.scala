package tk.gushizone.scala.collection

object ListTest {

    def main(args: Array[String]): Unit = {


        // 字符串列表
        val site: List[String] = List("Runoob", "Google", "Baidu")
        // 整型列表
        val nums: List[Int] = List(1, 2, 3, 4)
        // 空列表
        val empty: List[Nothing] = List()
        // 二维列表
        val dim: List[List[Int]] =
            List(
                List(1, 0, 0),
                List(0, 1, 0),
                List(0, 0, 1)
            )

//        可以使用 :: 和 Nil 初始化
//        // 字符串列表
//        val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
//        // 整型列表
//        val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
//        // 空列表
//        val empty = Nil
//        // 二维列表
//        val dim = (1 :: (0 :: (0 :: Nil))) ::
//            (0 :: (1 :: (0 :: Nil))) ::
//            (0 :: (0 :: (1 :: Nil))) :: Nil


        // 遍历
        nums.foreach(println)

        println(s"head: ${site.head}")
        println(s"tail: ${site.tail}")

        // 增
        val nums2 = nums.+:(0) // 首部添加元素
//        val nums2 = nums.::(0) // 首部添加元素
        println(s"nums2: $nums2")

        val nums3 = nums.:+(5) // 尾部添加元素
        println(s"nums3: $nums3")

//        val num4 = nums.++:(List(-2, -1, 0)) // 首部添加集合
        val num4 = nums.:::(List(-2, -1, 0)) // 首部添加集合
        println(s"num4: $num4")

        val num5 = nums.++(List(5, 6, 7)) // 尾部添加集合
        println(s"num5: $num5")


        // 删除
        val nums6: List[Int] = List(1, 2, 3, 4, 1)
        val nums7 = nums6.dropWhile(num => num < 3)
        println(s"nums7: $nums7")


        // 不可变元素不可修改


        // 查询
        println(s"nums(3):              ${nums(3)} ")
        println(s"nums.length:          ${nums.length} ")
        println(s"nums.isEmpty:         ${nums.isEmpty} ")
        println(s"nums.contains(3):     ${nums.contains(3)} ")

        // 高阶
        println(s"nums6.distinct:       ${nums6.distinct} ")
        println(s"nums6.reverse:        ${nums6.reverse} ")
        println(s"nums6.task(3):        ${nums6.take(3)} ")  // 获取前三个元素
        println(s"nums6.union:          ${nums6.union(List(1, 2, 5))} ")  // 合并集合


    }


}
