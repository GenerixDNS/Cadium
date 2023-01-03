/*
 * Created by Alexander J. on 02.01.2023.
 */

use std::any::Any;
use std::fmt;
use std::fmt::{Display, Formatter};
use std::net::{TcpListener, TcpStream};

pub static mut OBJECT_REGISTER: Option<Vec<NetworkObject>> = None;

pub unsafe fn init_register() {
    OBJECT_REGISTER = Some(vec![])
}

#[derive(Debug)]
pub struct UnableGetObjectError;

impl Display for UnableGetObjectError {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        write!(
            f,
            "Error: unable to get the right object by the surrendered input!"
        )
    }
}

pub struct NetworkObject {
    object: Box<dyn Any>,
}

impl From<TcpListener> for NetworkObject {
    fn from(listener: TcpListener) -> Self {
        Self {
            object: Box::new(listener),
        }
    }
}

impl From<TcpStream> for NetworkObject {
    fn from(stream: TcpStream) -> Self {
        Self {
            object: Box::new(stream),
        }
    }
}

pub struct UniqueObjectPointer {
    unique_identifier: usize,
}

impl UniqueObjectPointer {
    pub unsafe fn new(i: usize, obj: NetworkObject) -> Self {
        OBJECT_REGISTER.as_mut().unwrap().insert(i.clone(), obj);
        Self {
            unique_identifier: i,
        }
    }

    pub unsafe fn from(&self) -> Result<&NetworkObject, UnableGetObjectError> {
        let register = OBJECT_REGISTER.as_ref().unwrap();
        match register.get((&self.unique_identifier).clone()) {
            None => Err(UnableGetObjectError),
            Some(result) => Ok(result),
        }
    }
}

pub fn main() {}
