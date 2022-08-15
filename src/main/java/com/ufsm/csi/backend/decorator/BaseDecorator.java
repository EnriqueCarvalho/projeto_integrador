package com.ufsm.csi.backend.decorator;

import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public interface BaseDecorator {

    public void insertLog(StringBuilder str) throws IOException;
}
