import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object lsh {
	def main(args:Array[String]){
	  val conf=new SparkConf().setMaster("local").setAppName("LSH")
	  val sc=new SparkContext(conf)
	  val scRDD=sc.textFile("/home/hadoop/train.txt")
	  val min_hash=scRDD.zipWithIndex.mapPartitions(line=>{
		  var result:Iterator[((Int,Int),Int)]=Iterator()
	    while(line.hasNext){
	        var (x,y)=line.next
	      val split=x.split(" ");
	      val iter=split.iterator;
	      var word_pre:String=null
	      var list_number:Array[Int]=Array(9,9,9,9,9,9,9,9,9,9,9,9,9,9)
	      while(iter.hasNext){
	        var hash_number:String=""
	        var word_next=iter.next
	        if(word_pre!=null){
	          var word_now=word_pre+" "+word_next
	          hash_number=(word_pre.hashCode().toLong.abs*word_next.hashCode().toLong.abs).toString
	          if(hash_number.length()<=list_number.length){
	            hash_number+=hash_number
	            hash_number+="9999999999"
	          }
	          //println(word_pre,word_next,hash_number)
	          word_pre=word_next
	        }
	        else{
	          word_pre=word_next
	        }
	        if(hash_number!=""){
	          for(i <-0 until list_number.length){
	            if(list_number(i)>(hash_number.charAt(i).toInt-48)){
	              list_number(i)=hash_number.charAt(i).toInt-48
	            }
	          }
	        }
	      }
	      var string1:String=""
	      for(i<-0 until list_number.length){
	        result=result++Iterator(((i,list_number(i)),y.toInt))
	      }
	    }
	  result
	  }
	      ).groupByKey
	      val data=min_hash.mapPartitions(line=>{
	          var result:Iterator[((Int,Int),Int)]=Iterator()
	          while(line.hasNext){
	        	  var list1=line.next._2.toList
	        	 for(i<-0 until list1.length){
	        	   for (j<-(i+1) until list1.length){
	        	      result=result++Iterator(((list1(i),list1(j)),1))
	        	   }
	        	 }
	          }
	          result
	      }
	        
	      ).reduceByKey(_+_).reduce((x1,x2)=>{if(x1._2>x2._2) x1 else x2})
	      println(data)
	      //val zero=new collection.mutable.Set[(Int,Int),Int]()
	      //val result=data.collect.foreach(println)
	      sc.stop
	}
}