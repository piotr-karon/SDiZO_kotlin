package sample.helloworld

    fun main(){
        val myTable = MyTable()
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)
        myTable.addLast(2)
        myTable.addLast(3)


        myTable.addFirst(5)
        myTable.addAtRandomPosition(22)

        val str = myTable.toPrettyString()

        println(str)


    }
