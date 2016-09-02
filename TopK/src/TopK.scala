import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object  TopK {
  
  val K=20
	def main(args:Array[String]){
	  val conf=new SparkConf().setMaster("local").setAppName("TopK")
    val sc=new SparkContext(conf)
	  val scRDD=sc.textFile("/home/hadoop/train.txt")
	  val result=scRDD.flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey((x,y)=>x+y)
	  val count=result.mapPartitions(iter=>{
	    var list = List[(String,Int)]()
	    var tree=new Tree()
       while(iter.hasNext){
         val value=iter.next
         tree.putToHeap(value)
         println(value._1,value._2)
       }
	  tree.getFromHeap()
	  }
	
	  ).collect.foreach(println)
	  /*
	  var iter:Iterator()=count.iterator()
	  var tree=new Tree()
	  while(iter.hasNext){
	    val value=iter.next();
	    tree.putToHeap(value)
	  }
	  val outiter=tree.getFromHeap()
	  while(outiter.hasNext){
	    println("1")
	    println("词："+outiter.next._1+"词频："+outiter.next._2)
	  }
	  */
	  sc.stop

	}
	

	
}