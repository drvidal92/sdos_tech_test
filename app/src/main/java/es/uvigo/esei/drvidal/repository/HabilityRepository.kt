package es.uvigo.esei.drvidal.repository

import es.uvigo.esei.drvidal.dao.HabilityDao
import es.uvigo.esei.drvidal.entity.HabilityEntity

/**
 * Created by Denís Requejo on 2020-01-15.
 */
class HabilityRepository(private val habilityDao: HabilityDao) {

    fun insert(hability: HabilityEntity) {
        habilityDao.insert(hability)
    }

    fun insertAll(habilities: List<HabilityEntity>) {
        habilityDao.insertAll(habilities)
    }

    fun getAll() : List<HabilityEntity> {
        return habilityDao.getAll()
    }

}