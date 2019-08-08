package com.cbp.api.dao;

import java.util.ArrayList;
import com.cbp.api.models.Trainer;
import com.cbp.api.util.WrapperUtil;
import com.matrixone.apps.domain.util.ContextUtil;
import matrix.db.BusinessObject;
import matrix.db.BusinessObjectList;
import matrix.db.Context;
import matrix.db.Query;
import matrix.util.MatrixException;

public class TrainersDao {

	public static ArrayList<Trainer> getTrainers(Context context) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, false);
			Query query = new Query();
			query.setBusinessObjectType("A_Trainer");
			query.setBusinessObjectName("*");
			query.setBusinessObjectRevision("A");
			query.setExpandType(true);
			BusinessObjectList blist =  query.evaluate(context);
			ContextUtil.commitTransaction(context);
			return WrapperUtil.trainerList(context, blist);
		} catch (Exception e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}

	public static Trainer getTrainer(Context context, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, false);
			BusinessObject bo = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bo.open(context);
			trainer = WrapperUtil.trainer(context, bo);
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return trainer;
		} catch (Exception e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
	
	public static Trainer createTrainer(Context context, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bo.create(context, Trainer.POLICY);
			bo.open(context);
			//add attr
			//TODO create dynamic
			if(trainer.getDescription() != null) bo.setAttributeValue(context, "A_Description", trainer.getDescription());
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return trainer;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
	
	public static Trainer updateTrainer(Context context, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bo.open(context);
			//add attr
			//TODO create dynamic
		
			if(trainer.getDescription() != null) bo.setAttributeValue(context, "A_Description", trainer.getDescription());
			bo.close(context);
			ContextUtil.commitTransaction(context);
			return trainer;
		} catch (MatrixException e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
	
	public static Trainer deleteTrainer(Context context, Trainer trainer) {
		try {
			if(context == null) return null;
			ContextUtil.startTransaction(context, true);
			BusinessObject bo = new BusinessObject(trainer.getType(), trainer.getName(), trainer.getRevision(), context.getVault().toString());
			bo.open(context);
			trainer = WrapperUtil.trainer(context, bo);
			bo.close(context);
			bo.remove(context);
			ContextUtil.commitTransaction(context);
			return trainer;
		} catch (Exception e) {
			e.printStackTrace();
			ContextUtil.abortTransaction(context);
			return null;
		}
	}
}
