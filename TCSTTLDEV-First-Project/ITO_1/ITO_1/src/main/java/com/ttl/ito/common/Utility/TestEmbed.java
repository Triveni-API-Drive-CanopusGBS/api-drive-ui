//package com.ttl.ito.common.Utility;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.math.BigInteger;
//
//import javax.xml.namespace.QName;
//
//import com.microsoft.schemas.office.office.CTLock;
//import com.microsoft.schemas.office.office.CTOLEObject;
//import com.microsoft.schemas.office.office.OLEObjectDocument;
//import com.microsoft.schemas.office.office.STConnectType;
//import com.microsoft.schemas.office.office.STOLEDrawAspect;
//import com.microsoft.schemas.office.office.STOLEType;
//import com.microsoft.schemas.office.office.STTrueFalseBlank;
//import com.microsoft.schemas.vml.CTFormulas;
//import com.microsoft.schemas.vml.CTGroup;
//import com.microsoft.schemas.vml.CTImageData;
//import com.microsoft.schemas.vml.CTPath;
//import com.microsoft.schemas.vml.CTShape;
//import com.microsoft.schemas.vml.CTShapetype;
//import com.microsoft.schemas.vml.STExt;
//import com.microsoft.schemas.vml.STStrokeJoinStyle;
//import com.microsoft.schemas.vml.STTrueFalse;
//import org.apache.poi.hpsf.ClassID;
//import org.apache.poi.hpsf.ClassIDPredefined;
//import org.apache.poi.ooxml.POIXMLDocumentPart;
//import org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart;
//import org.apache.poi.ooxml.POIXMLFactory;
//import org.apache.poi.ooxml.POIXMLRelation;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.PackagePart;
//import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
//import org.apache.poi.poifs.filesystem.Ole10Native;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.util.IOUtils;
//import org.apache.poi.util.Units;
//import org.apache.poi.xslf.usermodel.XSLFRelation;
//import org.apache.poi.xwpf.usermodel.Document;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFPicture;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.xmlbeans.XmlCursor;
//import org.junit.Test;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
//
//public class TestEmbed {
//   
//    public static void embed() throws IOException, InvalidFormatException {
//        try (XWPFDocument doc = new XWPFDocument();
//            InputStream imgIS = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\TTL_Brand.png");
//            InputStream pdfIS = new FileInputStream("E:\\Kavya\\costsheet.pdf")) {
//
//            // add a pdf as an OLEContainer object
//            final MyRelations oleRelDef = MyRelations.OLE_OBJECT;
//            int oleNumber = doc.getPackage().getUnusedPartIndex(oleRelDef.getDefaultFileName());
//
//            RelationPart oleRel = doc.createRelationship(oleRelDef, MyFactory.inst, oleNumber, false);
//            try (OutputStream os = oleRel.getDocumentPart().getPackagePart().getOutputStream();
//                 POIFSFileSystem poifs = new POIFSFileSystem();) {
//                // embeds the contents -> double click and return changes the image to a preview
//                // embedPDF(poifs, pdfIS);
//                // add content via OLE package
//                embedPDFasPackage(poifs, pdfIS);
//                poifs.writeFilesystem(os);
//            }
//
//            // some dummy text
//            doc.createParagraph().createRun().setText("foobaa");
//            // add run containing the embedded image
//            XWPFRun r = doc.createParagraph().createRun();
//
//            String imgRel = doc.addPictureData(imgIS, Document.PICTURE_TYPE_PNG);
//            addOleShape1(r, imgRel, oleRel.getRelationship().getId());
//
//            // working with a picture shape doesn't work
//            // XWPFPicture xpic = r.addPicture(imgIS, Document.PICTURE_TYPE_PNG, "dummy.png", Units.pixelToEMU(100), Units.pixelToEMU(100));
//            // addOleShape2(r, xpic, oleRel.getRelationship().getId());
//
//            try (FileOutputStream fos = new FileOutputStream("D:\\megha\\embedpdf.docx")) {
//                doc.write(fos);
//            }
//        }
//    }
//
//    static void embedPDF(POIFSFileSystem poifs, InputStream pdfIS) throws IOException {
//        poifs.createDocument(pdfIS, "Contents");
//        poifs.getRoot().setStorageClsid(new ClassID("{B801CA65-A1FC-11D0-85AD-444553540000}"));
//    }
//
//    static void embedPDFasPackage(POIFSFileSystem poifs, InputStream pdfIS) throws IOException {
//        Ole10Native ole10Native = new Ole10Native("MyDummy PDF", "dummy.pdf", "dummy.pdf", IOUtils.toByteArray(pdfIS));
//        poifs.getRoot().setStorageClsid(ClassIDPredefined.OLE_V1_PACKAGE.getClassID());
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ole10Native.writeOut(bos);
//        poifs.createDocument(new ByteArrayInputStream(bos.toByteArray()), Ole10Native.OLE10_NATIVE);
//    }
//
//
//    static void addOleShape1(XWPFRun run, String imgRel, String oleRel) {
//        // TODO: increase with every call
//        int typeCounter = 1;
//        int shapeCounter = 1025;
//
//        CTR ctr = run.getCTR();
//
//        // move image into object container - this is valid regarding Ecma 376 - fifth edition ... POI uses still first edition :(
//        CTObject obj = ctr.addNewObject();
//        obj.setDxaOrig(BigInteger.valueOf(2190));
//        obj.setDyaOrig(BigInteger.valueOf(1440));
//
//        CTGroup grp = CTGroup.Factory.newInstance();
//        CTShapetype st = grp.addNewShapetype();
//        st.setCoordsize("21600,21600");
//        st.setFilled(STTrueFalse.F);
//        st.setId("_x0000_t"+typeCounter);
//        st.setPreferrelative(com.microsoft.schemas.office.office.STTrueFalse.T);
//        st.setPath2("m@4@5l@4@11@9@11@9@5xe");
//        st.setStroked(STTrueFalse.F);
//        st.addNewStroke().setJoinstyle(STStrokeJoinStyle.MITER);
//
//        CTFormulas form = st.addNewFormulas();
//        String[] clumsyRect = {
//            "if lineDrawn pixelLineWidth 0",
//            "sum @0 1 0",
//            "sum 0 0 @1",
//            "prod @2 1 2",
//            "prod @3 21600 pixelWidth",
//            "prod @3 21600 pixelHeight",
//            "sum @0 0 1",
//            "prod @6 1 2",
//            "prod @7 21600 pixelWidth",
//            "sum @8 21600 0",
//            "prod @7 21600 pixelHeight",
//            "sum @10 21600 0"
//        };
//        for (String cr : clumsyRect) {
//            form.addNewF().setEqn(cr);
//        }
//
//        CTPath path = st.addNewPath();
//        path.setGradientshapeok(STTrueFalse.T);
//        path.setConnecttype(STConnectType.RECT);
//        path.setExtrusionok(com.microsoft.schemas.office.office.STTrueFalse.F);
//
//        CTLock lock = st.addNewLock();
//        lock.setAspectratio(com.microsoft.schemas.office.office.STTrueFalse.T);
//        lock.setExt(STExt.EDIT);
//
//        CTShape shape = grp.addNewShape();
//        shape.setId("_x0000_i"+shapeCounter);
//        shape.setStyle("width:109.45pt;height:1in");
//        shape.setType("#_x0000_t"+typeCounter);
//        shape.setOle(STTrueFalseBlank.X);
//
//        CTImageData imgDat = shape.addNewImagedata();
//        imgDat.setId2(imgRel);
//        imgDat.setTitle("");
//
//        OLEObjectDocument oleParent = OLEObjectDocument.Factory.newInstance();
//        CTOLEObject ole = oleParent.addNewOLEObject();
//        ole.setDrawAspect(STOLEDrawAspect.CONTENT);
//        ole.setObjectID("_foobaa"+shapeCounter);
//        ole.setProgID("AcroExch.Document");
//        ole.setShapeID("_x0000_i"+shapeCounter);
//        ole.setType(STOLEType.EMBED);
//        ole.setId(oleRel);
//
//        XmlCursor objCur = obj.newCursor();
//        objCur.toFirstContentToken();
//
//        XmlCursor grpCur = grp.newCursor();
//        grpCur.copyXmlContents(objCur);
//        grpCur.dispose();
//
//        XmlCursor oleCur = oleParent.newCursor();
//        oleCur.copyXmlContents(objCur);
//        oleCur.dispose();
//
//        objCur.dispose();
//
//    }
//
//
//    static void addOleShape2(XWPFRun run, XWPFPicture xpic, String oleRel) {
//        final String drawNS = "http://schemas.microsoft.com/office/drawing/2010/main";
//        final String wordNS = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";
//
//        // TODO: increase with every call
//        int typeCounter = 1;
//        int shapeCounter = 1025;
//        CTOfficeArtExtensionList extLst = xpic.getCTPicture().getNvPicPr().getCNvPr().addNewExtLst();
//
//        CTOfficeArtExtension ext = extLst.addNewExt();
//        ext.setUri("{63B3BB69-23CF-44E3-9099-C40C66FF867C}");
//        XmlCursor cur = ext.newCursor();
//        cur.toEndToken();
//        cur.beginElement(new QName(drawNS, "compatExt", "a14"));
//        cur.insertNamespace("a14", drawNS);
//        cur.insertAttributeWithValue("spid", "_x0000_i"+shapeCounter);
//        cur.dispose();
//
//
//        CTR ctr = run.getCTR();
//
//        // move image into object container - this is valid regarding Ecma 376 - fifth edition ... POI uses still first edition :(
//        CTObject obj = ctr.addNewObject();
//        obj.setDxaOrig(BigInteger.valueOf(2190));
//        obj.setDyaOrig(BigInteger.valueOf(1440));
//
//        XmlCursor objCur = obj.newCursor();
//        objCur.toFirstContentToken();
//
//        XmlCursor drawCur = ctr.getDrawingArray(0).newCursor();
//        drawCur.moveXml(objCur);
//        drawCur.dispose();
//
//        objCur.beginElement("objectEmbed", wordNS);
//        objCur.insertAttributeWithValue("id", PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, oleRel);
////        objCur.insertAttributeWithValue("progId", wordNS, "AcroExch.Document");
////        objCur.insertAttributeWithValue("shapeId", wordNS, "_x0000_i"+shapeCounter);
////        objCur.insertAttributeWithValue("drawAspect", wordNS, "content");
//
////        OLEObjectDocument oleParent = OLEObjectDocument.Factory.newInstance();
////        CTOLEObject ole = oleParent.addNewOLEObject();
////        ole.setDrawAspect(STOLEDrawAspect.CONTENT);
////        ole.setObjectID("_1639082351");
////        ole.setProgID("AcroExch.Document");
////        ole.setShapeID("_x0000_i"+shapeCounter);
////        ole.setType(STOLEType.EMBED);
////        ole.setId(oleRel);
////
////        XmlCursor oleCur = oleParent.newCursor();
////        oleCur.copyXmlContents(objCur);
////        oleCur.dispose();
//
//        objCur.dispose();
//
//    }
//
//    static class MyRelations extends POIXMLRelation {
//
//        static final MyRelations OLE_OBJECT = new MyRelations(
//            XSLFRelation.OLE_OBJECT.getContentType(),
//            XSLFRelation.OLE_OBJECT.getRelation(),
//            "/word/embeddings/oleObject#.bin",
//            null
//        );
//
//        private MyRelations(String type, String rel, String defaultName, Class<? extends POIXMLDocumentPart> cls) {
//            super(type, rel, defaultName, cls);
//        }
//    }
//
//    static class MyFactory extends POIXMLFactory {
//        static final MyFactory inst = new MyFactory();
//
//        protected POIXMLRelation getDescriptor(String relationshipType) {
//            return MyRelations.OLE_OBJECT;
//        }
//
//        protected POIXMLDocumentPart createDocumentPart
//        (Class<? extends POIXMLDocumentPart> cls, Class<?>[] classes, Object[] values)
//                throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
//            return new MyObjectData();
//        }
//    }
//
//    static class MyObjectData extends POIXMLDocumentPart {
//
//        /**
//         * Create a new XSLFObjectData node
//         */
//        MyObjectData() { }
//
//        public MyObjectData(final PackagePart part) {
//            super(part);
//        }
//
//        /**
//         * XSLFObjectData objects store the actual content in the part directly without keeping a
//         * copy like all others therefore we need to handle them differently.
//         */
//        @Override
//        protected void prepareForCommit() {
//            // do not clear the part here
//        }
//
//
//        public void setData(final byte[] data) throws IOException {
//            try (final OutputStream os = getPackagePart().getOutputStream()) {
//                os.write(data);
//            }
//        }
//    }
//
//public static void main(String args[])
//{
//	try {
//		embed();
//	} catch (InvalidFormatException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
//}