package ss.dao;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ss.ProductManual;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoDAO {
	private static final String myFS = "myFS";
	private static final String myDB = "myDB";
	
	private static MongoDAO mongoDAO;
	private Mongo mongo;
	
	private MongoDAO(Mongo mongo) {
		this.mongo = mongo;
	}
	
	// Connect to MongoDB and Get MongoDAO instance
	public static synchronized MongoDAO getInstance() {
		if(mongoDAO!=null)
			return mongoDAO;
		
		try {
			Mongo m = new Mongo("localhost", 27017);
			mongoDAO = new MongoDAO(m);
			return mongoDAO;
			
		} catch (UnknownHostException e) {
			throw new RuntimeException("Error connecting to MongoDB.", e);
		}
	}
	
	// Get connection to myDB database
	public DB getMongoDB() {
		DB db = mongo.getDB(myDB);
		return db;
	}

	// Given the productId and the fileName, get internal MongoDB file name
	public String getMongoDBFileName(int productId, String fileName) {
		DB db = getMongoDB();
		
		DBCollection coll = db.getCollection("productManuals");
		BasicDBObject query = new BasicDBObject();
		query.put("productId", productId);
		query.put("fileName", fileName);
        DBCursor cur = coll.find(query);
        
        // Return the input stream;
        if(cur.hasNext()) {
        	BasicDBObject doc = (BasicDBObject) cur.next();
        	return doc.getString("mongoDBFileName");
        }
        else
        	return null;
	}
	
	// Get input stream from the file in MongoDB
	public InputStream getInputStream(int productId, String fileName) {
		DB db = getMongoDB();
		String mongoDBFileName = getMongoDBFileName(productId, fileName);
		
        if(mongoDBFileName!=null) {
        	// Get grid file system
        	GridFS fs = new GridFS(db, myFS);
        	// Retrieve file from MongoDB
        	GridFSDBFile myFile = fs.findOne(mongoDBFileName);
        	
        	return myFile.getInputStream();
        }
        else
        	throw new RuntimeException("Product manual not found.");
	}
	
	// Insert a Product Manual
	public void insertProductManual(int productId, String fileName, InputStream is) {
		DB db = getMongoDB();
		DBCollection coll = db.getCollection("productManuals");
		BasicDBObject doc = new BasicDBObject();
		doc.put("productId", productId);
		doc.put("fileName", fileName);
		String mongoDBFileName = "pm." + productId + "." + fileName;
		doc.put("mongoDBFileName", mongoDBFileName);
		saveFile(db, mongoDBFileName, is);
		coll.insert(doc);
	}
	
	// Write the contents of an input stream to a file in MongoDB
	private void saveFile(DB db, String mongoDBFileName, InputStream is) {
		// Get grid file system
		GridFS fs = new GridFS(getMongoDB(), myFS);

		// Save file to MongoDB
		GridFSInputFile myFile = fs.createFile(is);
		myFile.setFilename(mongoDBFileName);
		myFile.save();
	}
	
	// Get Product Manuals from MongoDB
	public ArrayList<ProductManual> getProductManuals(int productId) {
		DBCollection coll = getMongoDB().getCollection("productManuals");
		BasicDBObject query = new BasicDBObject();
		query.put("productId", productId);
        DBCursor cur = coll.find(query);
        
        ArrayList<ProductManual> manuals = new ArrayList<ProductManual>();
        while(cur.hasNext()) {
        	BasicDBObject doc = (BasicDBObject) cur.next();
        	ProductManual manual = new ProductManual();
        	manual.setProductId(doc.getInt("productId"));
        	manual.setFileName(doc.getString("fileName"));
        	manual.setMongoDBFileName(doc.getString("mongoDBFileName"));
        	manuals.add(manual);
        }
        return manuals;
	}
	
	// Delete product manual
	public void deleteProductManual(int productId, String fileName) {
		DB db = getMongoDB();
		
		// Find the product manual
		DBCollection coll = db.getCollection("productManuals");
		BasicDBObject query = new BasicDBObject();
		query.put("productId", productId);
		query.put("fileName", fileName);
        DBCursor cur = coll.find(query);
        
        // Delete the document
        if(cur.hasNext()) {
        	BasicDBObject doc = (BasicDBObject) cur.next();
        	
        	// Remove the actual file from GridFS
        	String mongoDBFileName = doc.getString("mongoDBFileName");
    		GridFS fs = new GridFS(db, myFS);
    		fs.remove(mongoDBFileName);
    		
        	// Remove the document from the collection
    		coll.remove(doc);
        }
	}
}
