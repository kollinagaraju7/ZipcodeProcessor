import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ZipCodeProcessor {

    static List<ZipCodeRange> minRanges = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Insert the zip code ranges by entering the numbers with \",\" separated. \nPlease input 'quit' to display the results.");
        try(Scanner input = new Scanner(System.in)){
            while(true) {
                String touple = input.next();
                if(touple.equalsIgnoreCase("quit")) {
                    printMinRanges();
                    break;

                }else {
                    prepareTuple(touple);
                }
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void prepareTuple(String touple) {
        List<Long> toupleList = Arrays.asList(touple.split(","))
                .stream()
                .mapToLong(Long::parseLong)
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        minRanges.add(new ZipCodeRange(toupleList.get(0), toupleList.get(1)));
        processMinRanges();
    }

    private static void printMinRanges() {
        processMinRanges();
        minRanges.stream().forEach(System.out::println);
    }

    private static void processMinRanges() {
        final List<ZipCodeRange> clonedList = new ArrayList<>(minRanges);
        clonedList.stream()
                .forEach(previous -> clonedList.stream().forEach(current-> compareAndSwap(current,previous)));
    }

    private static void compareAndSwap(ZipCodeRange current, ZipCodeRange previous) {
        if(current.getLowerValue() > previous.getLowerValue() && current.getLowerValue() < previous.getUpperValue() || current.getUpperValue() > previous.getLowerValue() && current.getUpperValue() < previous.getUpperValue()) {
            long min = Math.min(current.getLowerValue(), previous.getLowerValue());
            long max = Math.max(current.getUpperValue(), previous.getUpperValue());
            if( current.getLowerValue()> min && current.getUpperValue()<= max) {
                minRanges.remove(previous);
                minRanges.remove(current);
                minRanges.add(new ZipCodeRange(min, max));
            }
        }
    }
}
