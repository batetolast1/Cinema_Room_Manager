package cinema

import cinema.Cinema.Companion.initializeCinema

fun main() = initializeCinema().run()

const val SMALL_CINEMA_SIZE = 60
const val CHEAP_TICKET_PRICE = 8
const val NORMAL_TICKET_PRICE = 10

class Cinema private constructor(rows: Int, seatsInRow: Int) {

    private val rows: Int
    private val seatsInRow: Int
    private val frontRows: IntRange
    private val backRows: IntRange
    private val totalSeats: Int
    private val totalIncome: Int
    private var currentIncome: Int
    private var purchasedTickets: Int
    private var seats: List<MutableList<Seat>>

    companion object {
        fun initializeCinema(): Cinema {
            println("Enter the number of rows:")
            val rows = readLine()!!
            println("Enter the number of seats in each row:")
            val seats = readLine()!!

            return try {
                Cinema(rows.toInt(), seats.toInt())
            } catch (e: NumberFormatException) {
                initializeCinema()
            } catch (e: IllegalArgumentException) {
                initializeCinema()
            }
        }
    }

    init {
        validate(rows, seatsInRow)

        this.rows = rows
        this.seatsInRow = seatsInRow
        this.frontRows = 1..(rows / 2)
        this.backRows = (rows - rows / 2)..rows
        this.totalSeats = getTotalSeats(rows, seatsInRow)
        this.totalIncome = getTotalIncome()
        this.currentIncome = 0
        this.purchasedTickets = 0
        this.seats = List(rows) { MutableList(seatsInRow) { Seat.EMPTY } }
    }

    private fun getTotalSeats(rows: Int, seatsInRow: Int) = rows * seatsInRow

    private fun printMenu() {
        println(
            """
            
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
            """.trimIndent()
        )
    }

    private fun validate(rows: Int, seatsInRow: Int) {
        require(rows > 0) { "Rows must be greater than 0" }
        require(seatsInRow > 0) { "Seats in row must be greater than 0" }
    }

    private fun getTotalIncome(): Int {
        return if (isSmallCinema()) {
            NORMAL_TICKET_PRICE * seatsInRow
        } else {
            ((frontRows.count() * NORMAL_TICKET_PRICE) + (backRows.count() * CHEAP_TICKET_PRICE)) * seatsInRow
        }
    }

    private fun isSmallCinema() = totalSeats <= SMALL_CINEMA_SIZE

    fun run() {
        while (true) {
            printMenu()

            when (readLine()!!) {
                "1" -> printSeats()
                "2" -> buyTicket()
                "3" -> showStatistics()
                "0" -> break
            }
        }
    }

    private fun printSeats() {
        println(
            """
            
            Cinema:
            """.trimIndent()
        )

        print(" ")
        for (seatNumber in 1..seatsInRow) {
            print(" $seatNumber")
        }
        println()

        for ((index, row) in seats.withIndex()) {
            print("${index + 1}")
            for (seat in row) print(" ${seat.printValue}")
            println()
        }
    }

    private fun buyTicket() {
        while (true) {
            println(
                """
                    
                Enter a row number:
                """.trimIndent()
            )
            val row = readLine()!!

            println("Enter a seat number in that row:")
            val seatInRow = readLine()!!

            try {
                bookTicket(row.toInt(), seatInRow.toInt())

                println(
                    """

                    Ticket price: $${getTicketPrice(row.toInt())}
                    """.trimIndent()
                )

                return
            } catch (e: NumberFormatException) {
                println(
                    """
                    
                    Wrong input!
                    """.trimIndent()
                )
            } catch (e: IllegalArgumentException) {
                println(
                    """
                    
                    Wrong input!
                    """.trimIndent()
                )
            } catch (e: IllegalStateException) {
                println(
                    """

                    That ticket has already been purchased!
                    """.trimIndent()
                )
            }
        }
    }

    private fun bookTicket(row: Int, seatInRow: Int) {
        require(row in 1..rows)
        require(seatInRow in 1..seatsInRow)

        check(isSeatAvailable(row, seatInRow))

        seats[row - 1][seatInRow - 1] = Seat.TAKEN
        purchasedTickets++
        currentIncome += getTicketPrice(row)
    }

    private fun isSeatAvailable(row: Int, seatInRow: Int) = seats[row - 1][seatInRow - 1] == Seat.EMPTY

    private fun getTicketPrice(row: Int) =
        if (isSmallCinema() || row in frontRows) NORMAL_TICKET_PRICE else CHEAP_TICKET_PRICE

    private fun showStatistics() {
        val percentage = purchasedTickets.toDouble() / totalSeats * 100

        println(
            """
                
            Number of purchased tickets: $purchasedTickets
            Percentage: ${String.format("%.2f", percentage)}%
            Current income: $$currentIncome
            Total income: $$totalIncome
            """.trimIndent()
        )
    }
}
