package com.trains.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.trains.controller.PassengerController;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.entity.TicketInform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFWriterForTicket {
    private static final String fileName = "C://Users/ACER/IdeaProjects/TrainTsystems/src/main/resources/ticket.pdf";
    private static final String imageURL = "C://Users/ACER/IdeaProjects/TrainTsystems/src/main/webapp/ROOT/train.jpg";
    private static Logger logger = LoggerFactory.getLogger(PDFWriterForTicket.class);


    public static void writePDFOfticket(TicketInformDTO ticketInformDTO){
        Document document = new Document(PageSize.A4,50,50,50,50);

        try {


            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(new File(fileName)));
            document.open();

            Image image = Image.getInstance(imageURL);
            image.scaleAbsolute(600f,300f);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);

            Paragraph title = new Paragraph("Ticket");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph ticketInfText = new Paragraph("\n" +
                    "Train number is "+ticketInformDTO.getIdTrain()+"\n"+
                    "Departure Station is "+ticketInformDTO.getDepartureStation()+ "\n"+
                    "Arrival Station is "+ticketInformDTO.getArrivalStation()+"\n"+
                    "Departure Time is "+ticketInformDTO.getDepartureTime()+ "\n"+
                    "Arrival Time is "+ticketInformDTO.getArrivalTime()+"\n"+
                    "Passenger FIO is "+ticketInformDTO.getSurname()+" "+ticketInformDTO.getName()+"\n"+
                    "Passenger Birthday is "+ticketInformDTO.getBirthday());
            document.add(ticketInfText);
            document.close();

        } catch (DocumentException| IOException ex) {
            logger.error("File not found");
        }
    }
}
