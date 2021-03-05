package com.beeline.demo.cocktailapp.data.repositiory.base

import com.beeline.demo.cocktailapp.data.api.Api
import com.beeline.demo.cocktailapp.data.network.ResponseHandler

abstract class BaseRepository(api: Api, val responseHandler: ResponseHandler = ResponseHandler())
