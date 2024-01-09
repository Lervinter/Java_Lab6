public class Cinema {
    private int[][][] seatingArrangement;

    public Cinema() {
        
        seatingArrangement = new int[5][10][20];
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (seatingArrangement[hallNumber][row][seat] == 0) {
                seatingArrangement[hallNumber][row][seat] = 1;
            } else {
                System.out.println("Місце " + seat + " в ряду " + row + " у залі " + hallNumber + " вже заброньоване.");
            }
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            seatingArrangement[hallNumber][row][seat] = 0;
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < 10; row++) {
            for (int seat = 0; seat < 20 - numSeats + 1; seat++) {
                boolean available = true;
                for (int i = 0; i < numSeats; i++) {
                    if (seatingArrangement[hallNumber][row][seat + i] == 1) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема розміщення місць для залу " + hallNumber + ":");
        for (int row = 0; row < 10; row++) {
            for (int seat = 0; seat < 20; seat++) {
                System.out.print(seatingArrangement[hallNumber][row][seat] + " ");
            }
            System.out.println();
        }
    }

    public int[] findBestAvailable(int hallNumber, int numSeats) {
        for (int row = 0; row < 10; row++) {
            for (int seat = 0; seat < 20 - numSeats + 1; seat++) {
                boolean available = true;
                for (int i = 0; i < numSeats; i++) {
                    if (seatingArrangement[hallNumber][row][seat + i] == 1) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    return new int[]{row, seat};
                }
            }
        }
        return null;
    }

    public void autoBook(int hallNumber, int numSeats) {
        int[] bestAvailable = findBestAvailable(hallNumber, numSeats);
        if (bestAvailable != null) {
            int row = bestAvailable[0];
            int seat = bestAvailable[1];
            int[] seatsToBook = new int[numSeats];
            for (int i = 0; i < numSeats; i++) {
                seatsToBook[i] = seat + i;
            }
            bookSeats(hallNumber, row, seatsToBook);
            System.out.println("Автоматично заброньовано " + numSeats + " місць у ряду " + row +
                    " з номерами " + seat + " - " + (seat + numSeats - 1) + " у залі " + hallNumber);
        } else {
            System.out.println("Недостатньо доступних місць для автоматичного бронювання " + numSeats + " місць у залі " + hallNumber);
        }
    }
}
