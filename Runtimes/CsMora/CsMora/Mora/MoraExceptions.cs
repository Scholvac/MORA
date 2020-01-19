using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora
{
    public class MoraException : Exception
    {
        public MoraException() : base() { }

        public MoraException(string message) : base(message) { }

        public MoraException(string message, Exception innerException) : base(message, innerException) { }
    }

    public class MoraAdapterException : MoraException
    {
        public MoraAdapterException() : base() { }

        public MoraAdapterException(string message) : base(message) { }

        public MoraAdapterException(string message, Exception innerException) : base(message, innerException) { }
    }
}
