package com.cbp.api.dao;

import java.util.ArrayList;
import java.util.Map;

import com.cbp.api.models.Trainer;
import com.cbp.api.models.Training;
import com.cbp.api.util.WrapperUtil;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.DomainRelationship;
import com.matrixone.apps.domain.util.ContextUtil;
import matrix.db.BusinessObject;
import matrix.db.BusinessObjectList;
import matrix.db.Context;
import matrix.db.Query;
import matrix.db.RelationshipType;
import matrix.util.MatrixException;
import matrix.util.StringList;

public class TrainingsDao {

	public static ArrayList<Training> getTrainings(Context context) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, false);
			Query query = new Query();
			query.setBusinessObjectType("A_Training");
			query.setBusinessObjectName("*");
			query.setBusinessObjectRevision("A");
			query.setExpandType(true);
			BusinessObjectList blist =  query.evaluate(context);
			ContextUtil.commitTransaction(context);
			return WrapperUtil.trainingList(context, blist);
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Training getTrainer(Context context, Training training) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, false);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.open(context);
			//get related objs
			DomainObject dom = new DomainObject(bo);
			StringList slobj = new StringList();
			slobj.addElement("type");
			slobj.addElement("name");
			slobj.addElement("revision");
			slobj.addElement("attribute[A_Description]");
			
			Map ob = dom.getRelatedObject(context, "A_Training-Trainer", true, slobj, null);
			Trainer t = null;
			if(ob != null) {
				t = new Trainer(ob.get("type").toString(), ob.get("name").toString(), ob.get("revision").toString(), ob.get("attribute[A_Description]").toString());
			}
			bo.close(context);
			training.setTrainer(t);
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Training getTraining(Context context, Training training) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, false);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.open(context);
			training = WrapperUtil.training(context, bo);
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Training createTraining(Context context, Training training) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.create(context, Training.POLICY);
			bo.open(context);
			//add attr
			//TODO create dynamic
			if(training.getTopic() != null) bo.setAttributeValue(context, "A_Topic", training.getTopic());
			if(training.getDuration() != null)bo.setAttributeValue(context, "A_Duration", training.getDuration());
			bo.close(context);
			
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
	
	//Overloaded
	public static Training createTraining(Context context, Training training, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.create(context, Training.POLICY);
			bo.open(context);
			//add attr
			//TODO create dynamic
			if(training.getTopic() != null) bo.setAttributeValue(context, "A_Topic", training.getTopic());
			if(training.getDuration() != null)bo.setAttributeValue(context, "A_Duration", training.getDuration());
			BusinessObject bot = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bot.open(context);
			RelationshipType rel = new RelationshipType("A_Training-Trainer");
			DomainRelationship.connect(context, new DomainObject(bo), rel, new DomainObject(bot));
			bot.close(context);
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Training updateTraining(Context context, Training training) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.open(context);
			//add attr
			//TODO create dynamic

			if(training.getTopic() != null) bo.setAttributeValue(context, "A_Topic", training.getTopic());
			if(training.getDuration() != null) bo.setAttributeValue(context, "A_Duration", training.getDuration());
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
	
	//Overloaded
	public static Training updateTraining(Context context, Training training, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.open(context);
			//add attr
			//TODO create dynamic
			if(training.getTopic() != null) bo.setAttributeValue(context, "A_Topic", training.getTopic());
			if(training.getDuration() != null) bo.setAttributeValue(context, "A_Duration", training.getDuration());
			BusinessObject bot = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bot.open(context);
			RelationshipType rel = new RelationshipType("A_Training-Trainer");
			DomainRelationship.connect(context, new DomainObject(bo), rel, new DomainObject(bot));
			bot.close(context);
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Training deleteTraining(Context context, Training training) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(training.getType(), training.getName(), training.getRevision(), context.getVault().toString());
			bo.open(context);
			training = WrapperUtil.training(context, bo);
			//get related objs
			DomainObject dom = new DomainObject(bo);
			StringList slobj = new StringList();
			slobj.addElement("type");
			slobj.addElement("name");
			slobj.addElement("revision");
			slobj.addElement("attribute[A_Description]");
			
			Map ob = dom.getRelatedObject(context, "A_Training-Trainer", true, slobj, null);
			
			BusinessObject bot = new BusinessObject(ob.get("type").toString(), ob.get("name").toString(), ob.get("revision").toString(), context.getVault().getName());
			bot.remove(context);
			Trainer t = new Trainer(ob.get("type").toString(), ob.get("name").toString(), ob.get("revision").toString(), ob.get("attribute[A_Description]").toString());
			bo.close(context);
			bo.remove(context);
			ContextUtil.commitTransaction(context);
			training.setTrainer(t);
			return training;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

}
